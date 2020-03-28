var totalSeconds;
var onBreak = false;
var startedTimer = false;
var pomoCount = 0;
var timer;
var normalPomo = 25*60;
var shortBreak = 5*60;
var longBreak = 25*60;

initializePomo();

$("#start").on("click", function() {
  if(!startedTimer) {
    startCounting();
    startedTimer = true;
    if(!onBreak) $(this).text("Focus Up!");
    else $(this).text("Take a break!");
  }
});

// Function to initialize the pomo timer
function initializePomo() {
  if(!onBreak) {
    totalSeconds = normalPomo;
  } else {
    if(pomoCount % 4 != 0) totalSeconds = shortBreak;
    else totalSeconds = longBreak;
  }

  setClock();
}

// Function that starts the countdown
function startCounting() {
  for(var sec = 1; sec <= totalSeconds; sec++)
    timer = setTimeout(countDown, (1000*sec));
}

// Function to countdown the clock one second
function countDown() {
  totalSeconds--;
  setClock();
  // Reached the end of the timer!
  if(totalSeconds === 0) {
    if(!onBreak) incrementPomo();
    resetClock();
  }
}

// Function to reset the clock
function resetClock() {
  onBreak = !onBreak;
  startedTimer = !startedTimer;
  $("#start").text("Start Timer");
  initializePomo();
}

// Function to increase the pomo count
function incrementPomo() {
  pomoCount++;
  $("#count").text(pomoCount);
}

// Function to set the clock given the total seconds left
function setClock() {
  $("#minute").text(paddedNumber(Math.floor(totalSeconds/60)));
  $("#second").text(paddedNumber(totalSeconds%60));
}

// Function to pad numbers to ensure that they are two digits
function paddedNumber(num) {
  return (num < 10) ? ("0" + num) : num;
}

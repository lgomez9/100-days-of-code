var possiblePlayers = ["X", "O"];
var currPlayer;


$("#reset").on("click", function(){
  setCurrentPlayer(firstPlayer());

  $(".tile").each(function(tile){
    $(this).find('span').text("");
  });
});

$('#start').on("click", function(){
  setCurrentPlayer(firstPlayer());
});

$('.tile').on("click", function(){
  if(currPlayer == "X") {
    $(this).find('span').text("X");
    setCurrentPlayer(possiblePlayers[1]);
  }
  else {
    $(this).find('span').text("O");
    setCurrentPlayer(possiblePlayers[0]);
  }

});

function firstPlayer() {
  return possiblePlayers[Math.floor(Math.random() * 2)];
}

function setCurrentPlayer(curr) {
  currPlayer = curr;
  $('#currPlayer').text("Current Player: " + currPlayer);
}

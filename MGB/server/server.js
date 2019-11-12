const express = require("express");
const app = express();



var mysql = require('mysql');

var con = mysql.createConnection({
  host: "127.0.0.1",
  user: "root",
  password: "mysql",
  database: "spring"
});


/*con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
});*/
/**/ */



app.get('/getposts', (req, res) => {
con.connect(function(err) {
  if (err) 
  throw err;
  console.log('MySql Connected...');
  //Select all customers and return the result object:
  con.query("SELECT * FROM testing", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
    res.send(result);
  });
});
});
/*
app.get('/getposts', (req, res) => {
    
    con.query("SELECT * FROM testing", function (err, results, fields) {
        if(err) throw err;
        console.log(results);
        console.log(query);
        res.send('Posts fetched...');
    });
});*/


app.listen(7777,() => {
    console.log("server is running on port 7777");
});
module.exports = app;
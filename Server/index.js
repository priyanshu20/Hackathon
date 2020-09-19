//import packages
const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const path = require("path");
const { sendSuccess } = require("./utilities/helpers");
const { PORT } = require("./config/index");
require("dotenv").config();
console.log(process.env.PORT);
//setting express
const app = express();

//middlewares
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

//configurations
require("./config/dbconnection");

//global variables
app.use((req, res, next) => {
  res.locals.user = req.user || null;
  next();
});

//routes
app.use("/test", require("./routes/index"));
// app.use("/users", require("./routes/users"));

//404 route
app.get("*", (req, res) => {
  return sendSuccess(res, "API NOT FOUND!");
});

//setting up server
app.listen(PORT, (err) => {
  if (err) console.log("Error in running Server.");
  else console.log(`Server is up and running on Port ${PORT}`);
});

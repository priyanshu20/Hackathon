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

const http = require("http");
const server = http.createServer(app);

//starting up server
(async () => {
  try {
    await server.listen(PORT);
    console.info(`\nServer is up and running on Port ${PORT}`);
  } catch (err) {
    console.info("Error in running server.");
  }
})();

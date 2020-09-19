const mongoose = require("mongoose");
const { MONGO_URI } = require("./index");

//Connection string
const dburl = MONGO_URI;
console.log(dburl);
//map global promise
mongoose.Promise = global.Promise;
//Database connection
mongoose
  .connect(dburl, { useNewUrlParser: true })
  .then(() => {
    console.log("MongoDB Connected Successfully.");
  })
  .catch((err) => console.log(`Error in MongoDB Connectivity: ${err}`));

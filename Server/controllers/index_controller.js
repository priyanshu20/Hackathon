const { sendError, sendSuccess } = require("../utilities/helpers");
const mongoose = require("mongoose");
const User = require("../models/User");

module.exports.index = (req, res) => {
  return sendSuccess(res, "Welcome to the API!");
};

module.exports.createUser = async (req, res) => {
  let { name, email, password, age, latitude, longitude } = req.body;
  let exists = await User.find({ email });
  if (exists) return sendError(res, "User already exists", BAD_REQUEST);
  let user = new User({
    name,
    email,
    password,
    age,
    status: "busy",
    vCount: 0,
    latitude,
    longitude,
  });
  await user.save();
  return sendSuccess(res, "User Created");
};

const { sendError, sendSuccess } = require("../utilities/helpers");
const mongoose = require("mongoose");
const User = require("../models/User");

module.exports.index = (req, res) => {
  return sendSuccess(res, "Welcome to the API!");
};

module.exports.createDummyUser = async (req, res) => {
  let user = new User({
    name: "Dummy",
    email: "dummy@dummy.com",
    password: "password",
    age: 18,
    status: "available",
    vCount: 0,
  });
  await user.save();
  return sendSuccess(res, "Added dummy User!");
};

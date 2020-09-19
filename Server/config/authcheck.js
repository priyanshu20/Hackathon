// TODO DAY 2
const mongoose = require("mongoose");
const User = require("../models/User");
const { sendError } = require("../utilities/helpers");
const { BAD_REQUEST, FORBIDDEN } = require("../utilities/statusCodes");
const ObjectId = mongoose.Types.ObjectId;

module.exports.isLoggedIn = async (req, res, next) => {
  if (req.header("email")) {
    req.user = await User.findOne({ email: req.header("email") });
    return next();
  } else {
    return sendError(res, "Not authorized", FORBIDDEN);
  }
};

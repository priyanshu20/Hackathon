// TODO DAY 2
const mongoose = require("mongoose");
const { sendError } = require("../utilities/helpers");
const { BAD_REQUEST, FORBIDDEN } = require("../utilities/statusCodes");
const ObjectId = mongoose.Types.ObjectId;

module.exports.isLoggedIn = (req, res, next) => {
  if (req) {
    req.user = {};
    req.user.id = ObjectId("5f65c08bea9fc44cb09d649d");
    return next();
  } else {
    return sendError(res, "Not authorized", FORBIDDEN);
  }
};

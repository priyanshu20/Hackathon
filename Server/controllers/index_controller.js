const { sendError, sendSuccess } = require("../utilities/helpers");

module.exports.index = (req, res) => {
  return sendSuccess(res, "Welcome to the API!");
};

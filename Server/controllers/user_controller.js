const { sendError, sendSuccess } = require("../utilities/helpers");
const User = require("../models/User");
const { BAD_REQUEST, NOT_FOUND } = require("../utilities/statusCodes");

module.exports.createUser = async (req, res) => {
  let { name, email, password, age, latitude, longitude } = req.body;
  let exists = await User.findOne({ email });
  if (exists) return sendError(res, "User already exists", BAD_REQUEST);
  let user = new User({
    name,
    email,
    password,
    age,
    status: "busy",
    vCount: 0,
    location: {
      type: "Point",
      coordinates: [longitude, latitude],
    },
  });
  await user.save();
  return sendSuccess(res, "User Created");
};

module.exports.updateUser = async (req, res) => {
  let {
    name,
    email,
    password,
    age,
    latitude,
    longitude,
    uid,
    status,
  } = req.body;
  if (email) {
    let exists = await User.findOne({ email });
    if (exists) return sendError(res, "User already exists", BAD_REQUEST);
  }
  let user = await User.findById(uid);
  if (!user) return sendError(res, "User not found", NOT_FOUND);

  if (name) user.name = name;
  if (email) user.email = email;
  if (password) user.password = password;
  if (age) user.age = age;
  if (status) user.status = status;
  if (longitude) user.location.coordinates = [longitude, latitude];

  await user.save();
  return sendSuccess(res, "User details updated");
};

module.exports.deleteUser = async (req, res) => {
  let { uid } = req.body;
  let user = await User.findById(uid);
  if (!user) return sendError(res, "User not found", NOT_FOUND);
  await user.remove();
  return sendSuccess(res, "User deleted");
};

module.exports.getUsers = async (req, res) => {
  let users = await User.find();
  return sendSuccess(res, users);
};

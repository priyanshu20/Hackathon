const { sendError, sendSuccess } = require("../utilities/helpers");

const Ping = require("../models/Ping");
const { NOT_FOUND } = require("../utilities/statusCodes");

module.exports.addPing = async (req, res) => {
  let {
    tag,
    description,
    latitude,
    longitude,
    forKids,
    reward,
    vRequired,
  } = req.body;
  if (forKids & !reward) return sendError(res, "A KID NEEDS A REWARD!");
  if (!forKids) {
    forKids = false;
    reward = "Blessing";
  }
  if (!tag === "community") vRequired = 1;
  let ping = new Ping({
    tag,
    description,
    latitude,
    longitude,
    vRequired,
    forKids,
    reward,
    status: "pending",
    uid: req.user.id,
  });
  await ping.save();
  return sendSuccess(res, "Ping saved");
};

module.exports.getPings = async (req, res) => {
  let pings = await Ping.find({
    tag: { $ne: "emergency" },
    status: "pending",
  });
  return sendSuccess(res, pings);
};

module.exports.getEmergencies = async (req, res) => {
  let pings = await Ping.find({ tag: "emergency" });
  return sendSuccess(res, pings);
};

module.exports.updatePing = async (req, res) => {
  let { status, pid } = req.body;
  let ping = await Ping.findById(pid);
  if (!ping) return sendError(res, "Ping not found", NOT_FOUND);
  ping.status = status;
  await ping.save();
  return sendSuccess(res, "Ping was updated!");
};

module.exports.deletePing = async (req, res) => {
  let { pid } = req.body;
  let ping = await Ping.findById(pid);
  if (!ping) return sendError(res, "Ping not found", NOT_FOUND);
  await ping.remove();
  return sendSuccess(res, "Ping was deleted");
};

const { sendError, sendSuccess } = require("../utilities/helpers");

const Ping = require("../models/Ping");

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
    uid: req.user.id,
  });
  await ping.save();
  return sendSuccess(res, "Ping saved");
};

module.exports.getPings = async (req, res) => {
  let pings = await Ping.find({ tag: { $ne: "emergency" } });
  return sendSuccess(res, pings);
};

module.exports.getEmergencies = async (req, res) => {
  let pings = await Ping.find({ tag: "emergency" });
  return sendSuccess(res, pings);
};

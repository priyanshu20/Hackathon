const mongoose = require("mongoose");
const ObjectId = mongoose.Schema.Types.ObjectId;

const PingSchema = new mongoose.Schema(
  {
    tag: { type: String, required: true }, //emergency,general,community
    description: { type: String, required: true },
    vRequired: { type: Number },
    longitude: { type: Number, required: true },
    latitude: { type: Number, required: true },
    forKids: { type: Boolean, required: true },
    reward: { type: String }, // Only applicable on tasks for kids
    uid: { type: ObjectId, ref: "User", required: true },
    volunteers: [{ type: ObjectId, ref: "User" }],
    status: { type: String, required: true }, //pending,completed,occupied
  },
  { timestamps: true }
);

module.exports = Ping = mongoose.model("Ping", PingSchema);

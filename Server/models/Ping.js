const mongoose = require("mongoose");
const ObjectId = mongoose.Schema.Types.ObjectId;
mongoose.set("useCreateIndex", true);

const PingSchema = new mongoose.Schema(
  {
    tag: { type: String, required: true }, //emergency,general,community
    description: { type: String, required: true },
    vRequired: { type: Number },
    location: {
      type: { type: String },
      coordinates: [{ type: Number }],
    },
    forKids: { type: Boolean, required: true },
    reward: { type: String }, // Only applicable on tasks for kids
    uid: { type: ObjectId, ref: "User", required: true },
    volunteers: [{ type: ObjectId, ref: "User" }],
    status: { type: String, required: true }, //pending,completed,occupied
  },
  { timestamps: true }
);

PingSchema.index({ location: "2dsphere" });

module.exports = Ping = mongoose.model("Ping", PingSchema);

const mongoose = require("mongoose");
const bcrypt = require("bcryptjs");

const UserSchema = new mongoose.Schema(
  {
    name: { type: String, required: true },
    email: { type: String, required: true },
    password: { type: String, required: true },
    age: { type: Number },
    status: { type: String, required: true }, //available,busy
    vCount: { type: Number, required: true }, //number of tasks completed
    latitude: { type: Number, required: true },
    longitude: { type: Number, required: true },
  },
  { timestamps: true }
);

UserSchema.pre("save", async function (next) {
  this.email = String(this.email).trim().toLowerCase();
  this.name = String(this.name).trim();
  if (!this.isModified("password")) return next();
  let salt = await bcrypt.genSalt(10);
  let hash = await bcrypt.hash(this.password, salt);
  this.password = hash;
  next();
});

UserSchema.methods.isValidPwd = async function (password) {
  let isMatchPwd = await bcrypt.compare(password, this.password);
  return isMatchPwd;
};

module.exports = User = mongoose.model("User", UserSchema);

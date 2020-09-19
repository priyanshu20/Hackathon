const express = require("express");
const {
  getPings,
  addPing,
  getEmergencies,
} = require("../controllers/ping_controller");
const { isLoggedIn } = require("../config/authcheck");
const router = express.Router();

//import controllers

const { catchErrors } = require("../utilities/helpers");

router.get("/", catchErrors(isLoggedIn), catchErrors(getPings));
router.post("/", catchErrors(isLoggedIn), catchErrors(addPing));
router.get("/emergency", catchErrors(isLoggedIn), catchErrors(getEmergencies));

module.exports = router;

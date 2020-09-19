const express = require("express");
const {
  getPings,
  addPing,
  getEmergencies,
  updatePing,
  deletePing,
  volunteersNearBy,
} = require("../controllers/ping_controller");
const { isLoggedIn } = require("../config/authcheck");
const router = express.Router();

//import controllers

const { catchErrors } = require("../utilities/helpers");

router.get("/", catchErrors(isLoggedIn), catchErrors(getPings));
router.post("/", catchErrors(isLoggedIn), catchErrors(addPing));
router.get("/emergency", catchErrors(isLoggedIn), catchErrors(getEmergencies));
router.put("/", catchErrors(isLoggedIn), catchErrors(updatePing));
router.delete("/", catchErrors(isLoggedIn), catchErrors(deletePing));
router.get(
  "/near/:pid",
  catchErrors(isLoggedIn),
  catchErrors(volunteersNearBy)
);

module.exports = router;

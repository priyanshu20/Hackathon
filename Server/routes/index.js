const express = require("express");
const router = express.Router();

//import controllers
const { index, createDummyUser } = require("../controllers/index_controller");
const { catchErrors } = require("../utilities/helpers");

router.get("/dummy", catchErrors(createDummyUser));
router.get("/", catchErrors(index));

//export router
module.exports = router;

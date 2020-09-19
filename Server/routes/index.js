const express = require("express");
const router = express.Router();

//import controllers
const { index } = require("../controllers/index_controller");
const { catchErrors } = require("../utilities/helpers");
router.get("/", catchErrors(index));

//export router
module.exports = router;

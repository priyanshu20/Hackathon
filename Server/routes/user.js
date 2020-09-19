const express = require("express");
const router = express.Router();

//import controllers
const {
  getUsers,
  createUser,
  updateUser,
  deleteUser,
} = require("../controllers/user_controller");
const { catchErrors } = require("../utilities/helpers");

router.get("/", catchErrors(getUsers));
router.post("/", catchErrors(createUser));
router.put("/", catchErrors(updateUser));
router.delete("/", catchErrors(deleteUser));

//export router
module.exports = router;

// TODO DAY 2

module.exports.isLoggedIn = (req, res, next) => {
  if (req) {
    return next();
  } else {
    res.redirect("/notauth");
  }
};

const { OK } = require("./statusCodes");

module.exports.sendError = (res, message, status) => {
  res.status(status).json({
    message,
    error: true,
    data: null,
  });
};

module.exports.sendSuccess = (res, data, token) => {
  if (token) {
    return res.status(OK).header("x-auth-token", token).json({
      message: "success",
      error: false,
      data,
    });
  }
  res.status(OK).json({
    message: "success",
    error: false,
    data,
  });
};

module.exports.catchErrors = (middlewareFunction) => {
  return async (req, res, next) => {
    try {
      await middlewareFunction(req, res, next);
    } catch (err) {
      next(err);
    }
  };
};

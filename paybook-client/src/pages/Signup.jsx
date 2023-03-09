import "./css/Signup.css";
import { checkUsername, checkEmail, signup } from "../hooks/api";

import { Button, Form, Input, notification } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import {
  NAME_MIN_LENGTH,
  NAME_MAX_LENGTH,
  USERNAME_MIN_LENGTH,
  USERNAME_MAX_LENGTH,
  EMAIL_MAX_LENGTH,
  PASSWORD_MIN_LENGTH,
  PASSWORD_MAX_LENGTH,
} from "../constants";

export default function Signup() {
  const [name, setName] = useState({});
  const [username, setUsername] = useState({});
  const [email, setEmail] = useState({});
  const [password, setPassword] = useState({});

  const handleInputChange = (event, setFun, validationFun) => {
    const target = event.target;
    const inputValue = target.value;

    setFun({
      value: inputValue,
      ...validationFun(inputValue),
    });
  };

  const validateName = (name) => {
    if (name.length < NAME_MIN_LENGTH) {
      return {
        validateStatus: "error",
        errorMsg: `Name is too short (Minimum ${NAME_MIN_LENGTH} characters needed.)`,
      };
    } else if (name.length > NAME_MAX_LENGTH) {
      return {
        validationStatus: "error",
        errorMsg: `Name is too long (Maximum ${NAME_MAX_LENGTH} characters allowed.)`,
      };
    } else {
      return {
        validateStatus: "success",
        errorMsg: null,
      };
    }
  };

  const validateUsername = (username) => {
    if (username.length < USERNAME_MIN_LENGTH) {
      return {
        validateStatus: "error",
        errorMsg: `Username is too short (Minimum ${USERNAME_MIN_LENGTH} characters needed.)`,
      };
    } else if (username.length > USERNAME_MAX_LENGTH) {
      return {
        validationStatus: "error",
        errorMsg: `Username is too long (Maximum ${USERNAME_MAX_LENGTH} characters allowed.)`,
      };
    } else {
      return {
        validateStatus: null,
        errorMsg: null,
      };
    }
  };

  const validateEmail = (email) => {
    if (!email) {
      return {
        validateStatus: "error",
        errorMsg: "Email may not be empty",
      };
    }

    const EMAIL_REGEX = RegExp("[^@ ]+@[^@ ]+\\.[^@ ]+");
    if (!EMAIL_REGEX.test(email)) {
      return {
        validateStatus: "error",
        errorMsg: "Email not valid",
      };
    }

    if (email.length > EMAIL_MAX_LENGTH) {
      return {
        validateStatus: "error",
        errorMsg: `Email is too long (Maximum ${EMAIL_MAX_LENGTH} characters allowed)`,
      };
    }

    return {
      validateStatus: null,
      errorMsg: null,
    };
  };

  const validateUsernameAvailability = () => {
    const usernameValue = username.value;
    const usernameValidation = validateUsername(usernameValue);

    if (usernameValidation.validateStatus === "error") {
      setUsername({
        value: usernameValue,
        ...usernameValidation,
      });
      return;
    }

    setUsername({
      value: usernameValue,
      validateStatus: "validating",
      errorMsg: null,
    });

    checkUsernameAvailable(usernameValue);
  };

  const checkUsernameAvailable = async (usernameValue) => {
    await checkUsername(usernameValue)
      .then((res) => {
        setUsername({
          value: usernameValue,
          validateStatus: "success",
          errorMsg: null,
        });
      })
      .catch((err) => {
        const { data } = err.response;
        setUsername({
          value: usernameValue,
          validateStatus: "error",
          errorMsg: data.message ? data.message : null,
        });
      });
  };

  const validateEmailAvailability = () => {
    const emailValue = email.value;
    const emailValidation = validateEmail(emailValue);

    if (emailValidation.validateStatus === "error") {
      setEmail({
        value: emailValue,
        ...emailValidation,
      });
      return;
    }

    setEmail({
      value: emailValue,
      validateStatus: "validating",
      errorMsg: null,
    });

    checkEmailAvailable(emailValue);
  };

  const checkEmailAvailable = async (emailValue) => {
    await checkEmail(emailValue)
      .then((res) => {
        setEmail({
          value: emailValue,
          validateStatus: "success",
          errorMsg: null,
        });
      })
      .catch((err) => {
        const { data } = err.response;
        setEmail({
          value: emailValue,
          validateStatus: "error",
          errorMsg: data.message ? data.message : null,
        });
      });
  };

  const validatePassword = (password) => {
    if (password.length < PASSWORD_MIN_LENGTH) {
      return {
        validateStatus: "error",
        errorMsg: `Password is too short (Minimum ${PASSWORD_MIN_LENGTH} characters needed.)`,
      };
    } else if (password.length > PASSWORD_MAX_LENGTH) {
      return {
        validationStatus: "error",
        errorMsg: `Password is too long (Maximum ${PASSWORD_MAX_LENGTH} characters allowed.)`,
      };
    } else {
      return {
        validateStatus: "success",
        errorMsg: null,
      };
    }
  };

  const isFormInvalid = () => {
    return !(
      name.validateStatus === "success" &&
      username.validateStatus === "success" &&
      email.validateStatus === "success" &&
      password.validateStatus === "success"
    );
  };

  const handleOnFinish = () => {
    const signupRequest = {
      name: name.value,
      username: username.value,
      email: email.value,
      password: password.value,
    };

    signUpApi(signupRequest);
  };

  const navigate = useNavigate();

  const signUpApi = async (signupRequest) => {
    await signup(signupRequest)
      .then((res) => {
        notification.success({
          message: "Paybook App",
          description:
            "Thank you! You're successfully registered. Please Login to continue.",
        });
        navigate("/login");
      })
      .catch((err) => {
        notification.error({
          message: "Paybook App",
          description:
            err.message || "Sorry! Something went wrong. Please try again!",
        });
      });
  };

  return (
    <div className="signup-container">
      <h1 className="page-title">Sign Up</h1>
      <div className="signup-content">
        <Form
          className="signup-form"
          layout="vertical"
          onFinish={handleOnFinish}
        >
          <Form.Item
            label="Full name"
            validateStatus={name.validateStatus}
            help={name.errorMsg}
          >
            <Input
              size="large"
              name="name"
              autoComplete="off"
              placeholder="Your full name"
              value={name.value}
              onChange={(e) => handleInputChange(e, setName, validateName)}
            />
          </Form.Item>
          <Form.Item
            label="Username"
            hasFeedback
            validateStatus={username.validateStatus}
            help={username.errorMsg}
          >
            <Input
              size="large"
              name="username"
              autoComplete="off"
              placeholder="A unique username"
              value={username.value}
              onBlur={validateUsernameAvailability}
              onChange={(e) =>
                handleInputChange(e, setUsername, validateUsername)
              }
            />
          </Form.Item>
          <Form.Item
            label="Email"
            hasFeedback
            validateStatus={email.validateStatus}
            help={email.errorMsg}
          >
            <Input
              size="large"
              name="email"
              type="email"
              autoComplete="off"
              placeholder="Your email"
              value={email.value}
              onBlur={validateEmailAvailability}
              onChange={(e) => {
                handleInputChange(e, setEmail, validateEmail);
              }}
            />
          </Form.Item>
          <Form.Item
            label="Password"
            validateStatus={password.validateStatus}
            help={password.errorMsg}
          >
            <Input
              size="large"
              name="password"
              type="password"
              autoComplete="off"
              placeholder="A password between 6 to 20 characters"
              value={password.value}
              onChange={(e) => {
                handleInputChange(e, setPassword, validatePassword);
              }}
            />
          </Form.Item>
          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              size="large"
              className="signup-form-button"
              disabled={isFormInvalid()}
            >
              Sign up
            </Button>
            Already registerd? <Link to="/login">Login now!</Link>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
}

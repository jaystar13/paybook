import "./Signup.css";

import { Button, Form, Input } from "antd";
import { Link } from "react-router-dom";
import { useState } from "react";
import {
  NAME_MIN_LENGTH,
  NAME_MAX_LENGTH,
  USERNAME_MIN_LENGTH,
  USERNAME_MAX_LENGTH,
  EMAIL_MAX_LENGTH,
  PASSWORD_MIN_LENGTH,
  PASSWORD_MAX_LENGTH,
} from "../../constants";

export default function Signup() {
  const [name, setName] = useState({});
  const [username, setUsername] = useState({});

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

  const validateUsernameAvailability = () => {};

  return (
    <div className="signup-container">
      <h1 className="page-title">Sign Up</h1>
      <div className="signup-content">
        <Form className="signup-form" layout="vertical">
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
          <Form.Item label="Email" hasFeedback>
            <Input
              size="large"
              name="email"
              autoComplete="off"
              placeholder="Your email"
              onBlur={{}}
              onChange={{}}
            />
          </Form.Item>
          <Form.Item label="Password">
            <Input
              size="large"
              name="password"
              type="password"
              autoComplete="off"
              placeholder="A password between 6 to 20 characters"
              onChange={{}}
            />
          </Form.Item>
          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              size="large"
              className="signup-form-button"
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

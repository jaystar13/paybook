import "./css/Login.css";

import { useAuth } from "../hooks/useAuth";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Checkbox, Form, Input, notification } from "antd";
import { Link } from "react-router-dom";

export const Login = () => {
  const [form] = Form.useForm();

  const { login } = useAuth();

  const handleSubmit = () => {
    form.validateFields().then((values) => {
      const loginRequest = Object.assign({}, values);
      userLogin(loginRequest);
    });
  };

  const userLogin = async (data) => {
    const res = await login(data);

    if (res.status === 200) {
      notification.success({
        message: "Paybook App",
        description: "You're successfully logged in.",
      });
    } else if (res.status === 401) {
      notification.error({
        message: "Paybook App",
        description:
          "Your Username or Password is incorrect. Please try again!",
      });
    } else {
      notification.error({
        message: "Paybook App",
        description:
          res.message || "Sorry! Something went wrong. Please try again!",
      });
    }
  };

  const LoginForm = () => {
    return (
      <Form
        form={form}
        name="normal_login"
        className="login-form"
        initialValues={{
          remember: true,
        }}
        onFinish={handleSubmit}
        autoComplete="off"
      >
        <Form.Item
          name="usernameOrEmail"
          rules={[
            {
              required: true,
              message: "Please input your Username or Email!",
            },
          ]}
        >
          <Input
            prefix={<UserOutlined className="site-form-item-icon" />}
            size="large"
            name="usernameOrEmail"
            placeholder="Username or Email"
          />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: "Please input your Password!",
            },
          ]}
        >
          <Input
            prefix={<LockOutlined className="site-form-item-icon" />}
            size="large"
            name="password"
            type="password"
            placeholder="Password"
          />
        </Form.Item>
        <Form.Item>
          <Form.Item name="remember" valuePropName="checked" noStyle>
            <Checkbox>Remember me</Checkbox>
          </Form.Item>

          <Link className="login-form-forgot">Forgot password</Link>
        </Form.Item>

        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            size="large"
            className="login-form-button"
          >
            Log in
          </Button>
          Or <Link to="/signup">register now!</Link>
        </Form.Item>
      </Form>
    );
  };
  return (
    <div className="login-container">
      <h1 className="page-title">Login</h1>
      <div className="login-content">
        <LoginForm></LoginForm>
      </div>
    </div>
  );
};

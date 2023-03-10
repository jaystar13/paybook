import "./css/AppHeader.css";

import { Layout, Menu } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

const Header = Layout.Header;

export default function AppHeader() {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const menuItems = [
    {
      label: "Login",
      key: "login",
    },
    {
      label: "Signup",
      key: "signup",
    },
  ];

  const handleOnClick = (path) => {
    if (path) {
      navigate(path);
    }
  };

  return (
    <Header className="app-header">
      <div className="container">
        <div className="app-title">
          <Link to="/">Paybook App</Link>
        </div>
        <Menu
          className="app-menu"
          mode="horizontal"
          style={{ lineHeight: "64px" }}
          items={menuItems}
          onClick={() => handleOnClick("login")}
        ></Menu>
      </div>
    </Header>
  );
}

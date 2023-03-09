import "./css/AppHeader.css";

import { Layout, Menu } from "antd";
import { Link } from "react-router-dom";
import { useEffect } from "react";
const Header = Layout.Header;

export default function AppHeader() {
  useEffect(() => {
    console.log("AppHeader");
  }, []);
  const menuItems = [
    {
      label: <Link to="login">Login</Link>,
      key: "login",
    },
    {
      label: <Link to="signup">Signup</Link>,
      key: "signup",
    },
  ];

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
        ></Menu>
      </div>
    </Header>
  );
}

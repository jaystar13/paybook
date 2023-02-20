import "./AppHeader.css";

import { Layout, Menu } from "antd";
import { Link } from "react-router-dom";
const Header = Layout.Header;

export default function AppHeader() {
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

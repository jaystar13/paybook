import "./css/AppHeader.css";

import { Layout, Menu } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

const Header = Layout.Header;

export default function AppHeader({ menuItems }) {
  const navigate = useNavigate();
  const { user } = useAuth();

  const appMenuItems = () => {
    const logoutItem = {
      label: "Logout",
      key: "logout",
    };

    if (user) {
      return [...menuItems, logoutItem];
    }

    return [...menuItems];
  };

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
          items={appMenuItems()}
          onClick={({ key }) => handleOnClick(key)}
        ></Menu>
      </div>
    </Header>
  );
}

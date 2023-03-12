import "./css/AppLayout.css";
import { Navigate, useOutlet } from "react-router-dom";
import { Layout, notification } from "antd";
import AppHeader from "./AppHeader";
import { useAuth } from "../hooks/useAuth";

const { Content } = Layout;

export const HomeLayout = () => {
  const { user } = useAuth();
  const outlet = useOutlet();

  if (user) {
    return <Navigate to="/paybook/profile" replace />;
  }

  notification.config({
    placement: "topRight",
    top: 70,
    duration: 5,
  });

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
    <Layout className="app-container">
      <AppHeader menuItems={menuItems} />
      <Content className="app-content">
        <div className="container">{outlet}</div>
      </Content>
    </Layout>
  );
};

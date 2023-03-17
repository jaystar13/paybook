import "./css/AppLayout.css";
import { Navigate, useOutlet } from "react-router-dom";
import { Layout, notification } from "antd";
import AppHeader from "./AppHeader";
import { useAuth } from "../hooks/useAuth";

const { Content } = Layout;

export const AppLayout = () => {
  const { user } = useAuth();
  const outlet = useOutlet();

  notification.config({
    placement: "topRight",
    top: 70,
    duration: 0,
  });

  if (!user) {
    return <Navigate to="/" />;
  }

  const menuItems = [
    {
      label: "Profile",
      key: "profile",
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

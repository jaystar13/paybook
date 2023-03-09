import "./css/AppLayout.css";
import { Navigate, Outlet } from "react-router-dom";
import { Layout, notification } from "antd";
import AppHeader from "./AppHeader";
import { useAuth } from "../hooks/auth";
import { useEffect } from "react";

const { Content } = Layout;

const AppLayout = () => {
  const { user } = useAuth();

  notification.config({
    placement: "topRight",
    top: 70,
    duration: 0,
  });

  if (!user) {
    return <Navigate to="/" />;
  }

  return (
    <Layout className="app-container">
      <AppHeader />
      <Content className="app-content">
        <div className="container">
          <Outlet />
        </div>
      </Content>
    </Layout>
  );
};

export default AppLayout;

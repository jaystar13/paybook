import "./css/AppLayout.css";
import { Navigate, useOutlet } from "react-router-dom";
import { Layout, notification } from "antd";
import AppHeader from "./AppHeader";
import { useAuth } from "../hooks/useAuth";

const { Content } = Layout;

const HomeLayout = () => {
  const { user } = useAuth();
  const outlet = useOutlet();

  if (user) {
    return <Navigate to="/profile" replace />;
  }

  notification.config({
    placement: "topRight",
    top: 70,
    duration: 5,
  });

  return (
    <Layout className="app-container">
      <AppHeader />
      <Content className="app-content">
        <div className="container">{outlet}</div>
      </Content>
    </Layout>
  );
};

export default HomeLayout;

import "./AppLayout.css";
import { Outlet } from "react-router-dom";
import { Layout, notification } from "antd";
import AppHeader from "./AppHeader";

const { Content } = Layout;

const AppLayout = () => {
  notification.config({
    placement: "topRight",
    top: 70,
    duration: 0,
  });
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

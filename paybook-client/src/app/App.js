import "./App.css";

import AppHeader from "../common/AppHeader";

import { Layout, notification } from "antd";
import { Outlet } from "react-router-dom";

const { Content } = Layout;

export default function App() {
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
}

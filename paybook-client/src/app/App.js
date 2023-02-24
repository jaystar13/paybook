import "./App.css";

import AppHeader from "../common/AppHeader";
import Login from "../user/login/Login";

import { Layout } from "antd";
import { Outlet } from "react-router-dom";

const { Content } = Layout;

export default function App() {
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

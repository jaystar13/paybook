import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { ConfigProvider } from "antd";

const root = ReactDOM.createRoot(document.getElementById("root"));
const BASE_COLOR = "white";

root.render(
  <ConfigProvider
    theme={{
      token: {
        colorBgBase: BASE_COLOR,
      },
      components: {
        Layout: {
          colorBgHeader: BASE_COLOR,
          colorBgBody: BASE_COLOR,
        },
      },
    }}
  >
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </ConfigProvider>
);

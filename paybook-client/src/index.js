import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { router } from "./App2";
import { ConfigProvider } from "antd";
import { RouterProvider } from "react-router-dom";

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
      <RouterProvider router={router} />
    </React.StrictMode>
  </ConfigProvider>
);

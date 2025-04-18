import useAuthStore from "@/store/useAuthStore";
import { NavLink } from "react-router-dom";

const Sidebar = () => {
  const role = useAuthStore((state) => state.data.role);
  console.log("Role: ", role);

  const menus = {
    cashier: [
      { label: "Đơn hàng", path: "/cashier/orders" },
      { label: "Hoa Don", path: "/cashier/bills" },
      { label: "Thong ke", path: "/cashier/stats" },
    ],
    warehouse: [
      { label: "Tồn kho", path: "/warehouse/inventory" },
      { label: "Nhập hàng", path: "/warehouse/import" },
    ],
    manager: [
      { label: "Báo cáo", path: "/manager/report" },
      { label: "Nhân sự", path: "/manager/staff" },
    ],
    admin: [
      { label: "Người dùng", path: "/admin/users" },
      { label: "Cài đặt", path: "/admin/settings" },
    ],
  };

  return (
    <aside className="w-64 bg-base-300 h-full p-4">
      <p className="text-3xl font-playwrite">MyCoffe</p>
      <ul className="menu">
        {menus[role]?.map((item) => (
          <li key={item.path}>
            <NavLink to={item.path}>{item.label}</NavLink>
          </li>
        ))}
      </ul>
    </aside>
  );
};

export default Sidebar;

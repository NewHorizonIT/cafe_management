import useAuthStore from "@/store/useAuthStore";
import { NavLink } from "react-router-dom";

const Sidebar = () => {
  const role = useAuthStore((state) => state.data.role);
  console.log("Role: ", role);

  const menus = {
    cashier: [
      { label: "Đơn hàng", path: "/cashier/orders" },
      { label: "Hóa đơn", path: "/cashier/bills" },
      { label: "Thống kê", path: "/cashier/stats" },
    ],
    warehouser: [
      { label: "Tồn kho", path: "/warehouse/materials" },
      { label: "Sản phẩm", path: "/warehouse/products" },
      { label: "Nhà cung cấp", path: "/warehouse/suppliers" },
      { label: "Nhập hàng", path: "/warehouse/purchases" },
      { label: "Thống kê", path: "/warehouse/stats" },
    ],
    manager: [
      { label: "Báo cáo", path: "/manager/report" },
      { label: "Nhân sự", path: "/manager/staff" },
    ],
    admin: [
      { label: "Người dùng", path: "/admin/users" },
      { label: "Thống kê", path: "/admin/stats" },
      { label: "Doanh nghiệp", path: "/admin/enterprise" },
    ],
  };

  return (
    <aside className="w-64 bg-base-300 h-full py-4">
      <p className="text-3xl font-playwrite text-center">MyCoffe</p>
      <ul className="menu w-full p-0 my-5 flex flex-col gap-2">
        {menus[role]?.map((item) => (
          <NavLink
            key={item.path}
            end
            className={({ isActive }) =>
              isActive
                ? "bg-primary text-primary-content flex items-center p-4"
                : "w-full h-full flex items-center p-4 hover:bg-primary hover:text-primary-content"
            }
            to={item.path}
          >
            {item.label}
          </NavLink>
        ))}
      </ul>
    </aside>
  );
};

export default Sidebar;

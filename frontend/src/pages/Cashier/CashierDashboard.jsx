import DashboardLayout from "@/layouts/DashboardLayout";

const CashierDashboard = () => {
  return (
    <div>
      <h2 className="text-2xl font-semibold mb-4">Chào mừng thu ngân!</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div className="card bg-base-100 shadow">
          <div className="card-body">
            <h3 className="card-title">Tổng đơn hôm nay</h3>
            <p>15 đơn hàng</p>
          </div>
        </div>
        <div className="card bg-base-100 shadow">
          <div className="card-body">
            <h3 className="card-title">Doanh thu hôm nay</h3>
            <p>1,200,000₫</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CashierDashboard;

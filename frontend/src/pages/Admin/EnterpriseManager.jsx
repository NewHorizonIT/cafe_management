import { Button, CardProduct, Table } from "@/components/ui";
import React, { useState } from "react";

const EnterpriseManager = () => {
  const [searchTerm, setSearchTerm] = useState("");

  const filteredInventoryData = mockInventoryData.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <h1>Quản lý doanh nghiệp</h1>

      {/* Section: Sales Statistics */}
      <section>
        <h2>Báo cáo bbán hàng</h2>
        <Table
          data={mockSalesData}
          columns={[
            { header: "NgàyNgày", accessor: "date" },
            { header: "Tổng bán", accessor: "totalSales" },
            { header: "Tổng hóa đơn", accessor: "totalOrders" },
          ]}
        />
      </section>

      {/* Section: Purchase Statistics */}
      <section>
        <h2>Báo cáo nhập kho</h2>
        <Table
          data={mockPurchaseData}
          columns={[
            { header: "Nhà cung cấp", accessor: "supplier" },
            { header: "Tổng nguyên liệu ", accessor: "totalItems" },
            { header: "Tổng tiềntiền", accessor: "totalCost" },
          ]}
        />
      </section>

      {/* Section: Inventory Overview */}
      <section>
        <h2>Quản lý kho</h2>
        <input
          type="text"
          placeholder="Tìm kiếm nguyên liệu..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border px-3 py-2 mb-4 w-full"
        />
        <div style={{ display: "flex", gap: "1rem", flexWrap: "wrap" }}>
          {filteredInventoryData.map((item) => (
            <CardProduct key={item.id} product={item} />
          ))}
        </div>
      </section>

      {/* Section: Actions */}
      <section>
        <h2>Hành động</h2>
        <Button eventHandler={() => alert("Accessing all user permissions...")}>
          Tất cả quyền người dùng
        </Button>
      </section>
    </div>
  );
};

// Mock data for demonstration purposes
const mockSalesData = [
  { date: "2025-04-01", totalSales: "$5000", totalOrders: 50 },
  { date: "2025-04-02", totalSales: "$3000", totalOrders: 30 },
];

const mockPurchaseData = [
  { supplier: "Supplier A", totalItems: 100, totalCost: "$2000" },
  { supplier: "Supplier B", totalItems: 50, totalCost: "$1000" },
];

const mockInventoryData = [
  { id: 1, name: "Coffee Beans", price: "$20", stock: 100 },
  { id: 2, name: "Milk", price: "$5", stock: 200 },
];

export default EnterpriseManager;

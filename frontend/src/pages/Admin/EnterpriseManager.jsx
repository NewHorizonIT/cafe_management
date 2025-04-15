import { Button, CardProduct, Table } from "@/components/ui";
import React from "react";

const EnterpriseManager = () => {
  return (
    <div>
      <h1>Enterprise Manager Dashboard</h1>

      {/* Section: Sales Statistics */}
      <section>
        <h2>Sales Statistics</h2>
        <Table
          data={mockSalesData}
          columns={[
            { header: "date", accessor: "date" },
            { header: "totalSales", accessor: "totalSales" },
            { header: "totalOrders", accessor: "totalOrders" },
          ]}
        />
      </section>

      {/* Section: Purchase Statistics */}
      <section>
        <h2>Purchase Statistics</h2>
        <Table
          data={mockPurchaseData}
          columns={[
            { header: "supplier", accessor: "supplier" },
            { header: "totalItems", accessor: "totalItems" },
            { header: "totalCost", accessor: "totalCost" },
          ]}
        />
      </section>

      {/* Section: Inventory Overview */}
      <section>
        <h2>Inventory Overview</h2>
        <div style={{ display: "flex", gap: "1rem", flexWrap: "wrap" }}>
          {mockInventoryData.map((item) => (
            <CardProduct key={item.id} product={item} />
          ))}
        </div>
      </section>

      {/* Section: Actions */}
      <section>
        <h2>Actions</h2>
        <Button eventHandler={() => alert("Accessing all user permissions...")}>
          Manage Permissions
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

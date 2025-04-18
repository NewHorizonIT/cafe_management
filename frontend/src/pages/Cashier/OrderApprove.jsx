import { FilterIcon } from "@/components/icons";
import OrderModal from "@/components/pages/Cashier/OrderModal";
import { Button } from "@/components/ui";
import Modal from "@/components/ui/Modal";
import Table from "@/components/ui/Table";
import { orders, columns } from "@/mock/orders";
import React, { useState } from "react";

const OrderApprove = () => {
  const [selectedOrder, setSelectedOrder] = useState(null);
  const [selectFilter, setSelectFilter] = useState(null);
  const handleRowClick = (order) => {
    setSelectedOrder(order);
  };

  const handleFilter = (e) => {
    if (!selectFilter) {
      setSelectFilter(e.target.value);
    } else {
      setSelectFilter(null);
    }
  };
  return (
    <div>
      <div className="flex gap-4 pb-5">
        <label class="input">
          <svg
            class="h-[1em] opacity-50"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
          >
            <g
              stroke-linejoin="round"
              stroke-linecap="round"
              stroke-width="2.5"
              fill="none"
              stroke="currentColor"
            >
              <circle cx="11" cy="11" r="8"></circle>
              <path d="m21 21-4.3-4.3"></path>
            </g>
          </svg>
          <input type="search" required placeholder="Search" />
        </label>
        <Button
          className="px-3 py-2"
          lable={<FilterIcon />}
          eventHandler={handleFilter}
        />
      </div>
      <Table
        columns={columns}
        data={orders}
        onRowClick={handleRowClick}
      ></Table>

      {/* Modal chi tiết đơn */}
      <OrderModal
        isOpen={!!selectedOrder}
        onClose={() => setSelectedOrder(null)}
        order={selectedOrder}
      ></OrderModal>
    </div>
  );
};

export default OrderApprove;

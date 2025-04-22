import { FilterIcon } from "@/components/icons";
import Modal from "@/components/ui/Modal";
import { Button } from "@/components/ui";
import Table from "@/components/ui/Table";
import React, { useState } from "react";
import { orders, columns } from "@/mock/orders";
import ItemTable from "@/components/ui/ItemTable";

const OrderApprove = () => {
  const [selectedOrder, setSelectedOrder] = useState(null);
  const [selectFilter, setSelectFilter] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleRowClick = (order) => {
    setSelectedOrder(order);
    setIsModalOpen(true);
  };

  const handleFilter = (e) => {
    setSelectFilter(selectFilter ? null : e.target.value);
  };

  const handleConfirmOrder = () => {
    if (selectedOrder) {
      selectedOrder.status = "Confirmed";
      setIsModalOpen(false);
      setSelectedOrder(null);
    }
  };

  const filteredOrders = selectFilter
    ? orders.filter((order) => order.status === selectFilter)
    : orders;

  return (
    <div>
      <div className="flex gap-4 pb-5">
        <label className="input">
          <svg
            className="h-[1em] opacity-50"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
          >
            <g
              strokeLinejoin="round"
              strokeLinecap="round"
              strokeWidth="2.5"
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
        data={filteredOrders}
        onRowClick={handleRowClick}
      />

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        {selectedOrder && (
          <div>
            <h2>Order Details</h2>
            <p>
              <strong>Order ID:</strong> {selectedOrder.id}
            </p>
            <p>
              <strong>Customer:</strong> {selectedOrder.customer}
            </p>
            <p>
              <strong>Status:</strong> {selectedOrder.status}
            </p>
            <p>
              <strong>Total:</strong> ${selectedOrder.total}
            </p>
            <p>
              <strong>Items:</strong>
            </p>
            <ItemTable items={selectedOrder.items} />
            <Button
              className="mt-4"
              lable="Confirm Order"
              eventHandler={handleConfirmOrder}
            />
          </div>
        )}
      </Modal>
    </div>
  );
};

export default OrderApprove;

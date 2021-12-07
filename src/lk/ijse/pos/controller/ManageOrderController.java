package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.view.tdm.OrderDetailTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class ManageOrderController {

    private final OrderBO OrderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.ORDER);

    public AnchorPane root;
    public JFXComboBox cmbItemCode;
    public Label lblId;
    public Label lblDate;
    public JFXComboBox cmbCustomerId;
    public JFXButton btnAddToCart;
    public TableView<OrderDetailTM> tblOrderDetails;
    public JFXButton btnPlaceOrder;
    public Label lblTotal;
    public JFXTextField txtCustomerName;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscount;
    public JFXTextField txtQty;
    public JFXButton btnPrintBill;
    public JFXButton btnCancel;
    private String orderId;

    public void initialize() {

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<OrderDetailTM, Button> lastCol = (TableColumn<OrderDetailTM, Button>) tblOrderDetails.getColumns().get(5);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Remove");

            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        orderId = generateNewOrderId();
        lblId.setText("Order ID: " + orderId);
        lblDate.setText(LocalDate.now().toString());
        btnPlaceOrder.setDisable(true);
        btnPrintBill.setDisable(true);
        btnAddToCart.setDisable(true);
        txtCustomerName.setFocusTraversable(false);
        txtCustomerName.setEditable(false);
        txtDescription.setFocusTraversable(false);
        txtDescription.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);
        txtDiscount.setOnAction(event -> btnAddToCart.fire());
        txtDiscount.setEditable(false);
        txtQty.setOnAction(event -> btnAddToCart.fire());
        txtQty.setEditable(false);

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisablePlaceOrderButton();

            if (newValue != null) {
                try {
                    try {
                        if (!existCustomer(newValue + "")) {
                            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + newValue + "").show();
                        }
                        CustomerDTO customerDTO = null;
                        try {
                            customerDTO = OrderBO.searchCustomer(newValue + "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        txtCustomerName.setText(customerDTO.getName());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + newValue + "" + e).show();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtCustomerName.clear();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
            txtQty.setEditable(newItemCode != null);
            txtDiscount.setEditable(newItemCode != null);
            btnAddToCart.setDisable(newItemCode == null);

            if (newItemCode != null) {
                try {
                    if (!existItem(newItemCode + "")) {
                        new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + newItemCode + "").show();
                    }

                    ItemDTO item = null;
                    try {
                        item = OrderBO.searchItem(newItemCode + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    txtDescription.setText(item.getDescription());
                    txtUnitPrice.setText(item.getUnitPrice().setScale(2).toString());
                    Optional<OrderDetailTM> optOrderDetail = tblOrderDetails.getItems().stream().filter(detail -> detail.getCode().equals(newItemCode)).findFirst();
                    txtQtyOnHand.setText((optOrderDetail.isPresent() ? item.getQtyOnHand() - optOrderDetail.get().getQty() : item.getQtyOnHand()) + "");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                txtDescription.clear();
                txtQty.clear();
                txtQtyOnHand.clear();
                txtDiscount.clear();
                txtUnitPrice.clear();
            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {
            if (selectedOrderDetail != null) {
                cmbItemCode.setDisable(true);
                cmbItemCode.setValue(selectedOrderDetail.getCode());
                btnAddToCart.setText("Update");
                txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText()) + selectedOrderDetail.getQty() + "");
                txtDiscount.setText((txtDiscount.getText()) + selectedOrderDetail.getDiscount() + "");
                txtQty.setText(selectedOrderDetail.getQty() + "");
            } else {
                btnAddToCart.setText("Add");
                cmbItemCode.setDisable(false);
                cmbItemCode.getSelectionModel().clearSelection();
                txtQty.clear();
                txtDiscount.clear();
            }
        });

        loadAllCustomerIds();
        loadAllItemCodes();
        lblDate.setText(LocalDate.now().toString());
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return OrderBO.ifItemExist(code);
    }

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return OrderBO.ifCustomerExist(id);
    }

    private void loadAllCustomerIds() {
        ArrayList<CustomerDTO> all = null;
        try {
            all = OrderBO.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (CustomerDTO customerDTO : all) {
            cmbCustomerId.getItems().add(customerDTO.getId());
        }
    }

    private void loadAllItemCodes() {
        ArrayList<ItemDTO> all = null;
        try {
            all = OrderBO.getAllItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ItemDTO dto : all) {
            cmbItemCode.getItems().add(dto.getItemCode());
        }
    }

    public String generateNewOrderId() {
        try {
            return OrderBO.generateNewOrderId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void calculateTotal() {
        double total = 0;
        for (OrderDetailTM detail : tblOrderDetails.getItems()) {
            total = total + detail.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    private void enableOrDisablePlaceOrderButton() {
        btnPlaceOrder.setDisable(!(cmbCustomerId.getSelectionModel().getSelectedItem() != null && !tblOrderDetails.getItems().isEmpty()));
        btnPrintBill.setDisable(!(cmbCustomerId.getSelectionModel().getSelectedItem() != null && !tblOrderDetails.getItems().isEmpty()));
    }

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/pos/view/Dashboard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("DASHBOARD   |   YASIRU DAHANAYAKA");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 ||
                Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;
        } else if (!txtDiscount.getText().matches("\\d+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Discount").show();
        }

        String itemCode = cmbItemCode.getValue().toString();
        String description = txtDescription.getText();
        double discount = Double.parseDouble(txtDiscount.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = unitPrice * (qty) - qty * (unitPrice * (discount) / 100);

        boolean exists = tblOrderDetails.getItems().stream().anyMatch(detail -> detail.getCode().equals(itemCode));

        if (exists) {
            OrderDetailTM orderDetailTM = tblOrderDetails.getItems().stream().filter(detail -> detail.getCode().equals(itemCode)).findFirst().get();

            if (btnAddToCart.getText().equalsIgnoreCase("Update")) {
                orderDetailTM.setQty(qty);
                orderDetailTM.setTotal(total);
                orderDetailTM.setDiscount(discount);
                tblOrderDetails.getSelectionModel().clearSelection();
            } else {
                orderDetailTM.setQty(orderDetailTM.getQty() + qty);
                total = orderDetailTM.getQty() * unitPrice;
                orderDetailTM.setTotal(total);
            }
            tblOrderDetails.refresh();
        } else {
            tblOrderDetails.getItems().add(new OrderDetailTM(itemCode, description, qty, discount, total));
        }
        cmbItemCode.getSelectionModel().clearSelection();
        cmbItemCode.requestFocus();
        calculateTotal();
        enableOrDisablePlaceOrderButton();
        cmbCustomerId.setDisable(true);
        txtCustomerName.setEditable(false);
    }

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {
        boolean b = saveOrder(orderId, cmbCustomerId.getValue().toString(), Date.valueOf(lblDate.getText()), Time.valueOf(LocalTime.now()),
                tblOrderDetails.getItems().stream().map(tm -> new OrderDetailsDTO(orderId,
                        tm.getCode(), tm.getQty(), tm.getDiscount())).collect(Collectors.toList()), Double.parseDouble(lblTotal.getText()));

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Successfully Done").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }

        orderId = generateNewOrderId();
        lblId.setText("Order Id: " + orderId);
        cmbCustomerId.getSelectionModel().clearSelection();
        cmbItemCode.getSelectionModel().clearSelection();
        tblOrderDetails.getItems().clear();
        txtDiscount.clear();
        txtQty.clear();
        calculateTotal();
        cmbCustomerId.setDisable(false);
    }

    public boolean saveOrder(String orderId, String customerId, Date orderDate, Time orderTime, List<OrderDetailsDTO> orderDetails, double total) {
        try {
            OrderDTO orderDTO = new OrderDTO(orderId, customerId, orderDate, orderTime, orderDetails, total);
            return OrderBO.purchaseOrder(orderDTO);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ItemDTO findItem(String code) {
        try {
            return OrderBO.searchItem(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printOrderDetailOnAction(ActionEvent actionEvent) {
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        orderId = generateNewOrderId();
        lblId.setText("Order Id: " + orderId);
        cmbCustomerId.getSelectionModel().clearSelection();
        txtCustomerName.clear();
        cmbItemCode.getSelectionModel().clearSelection();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDiscount.clear();
        txtQty.clear();
        cmbCustomerId.setDisable(false);
        tblOrderDetails.getItems().clear();
        calculateTotal();
    }
}

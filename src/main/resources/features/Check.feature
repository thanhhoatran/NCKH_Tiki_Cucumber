Feature: Check add to cart successful
  Scenario: Verify that product add to cart successfully.
    Given  A user is on website with "chrome", url: "https://tiki.vn/" and size(1300,900)
    When Hover on "Đăng nhập" field and click on "Đăng nhập" link.
    When Enter all valid data in all field of Login form.
    When Click on "Đăng nhập" button.
    When Enter a valid data into Search field.
    When Click on "Tìm kiếm" button of search form.
    When Click on a product.
    When Click on "CHỌN MUA" button of product.
    When Click on "Thoát tài khoản" button of account.
    When User login again.
    When Click on "Giỏ hàng" menu.
    Then The products of cart still displayed.
    When Delete.
   # When "Thêm vào giỏ hàng thành công" popup is displayed.


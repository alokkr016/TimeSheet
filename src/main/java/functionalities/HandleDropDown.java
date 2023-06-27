 


 class HandleDropDown{
    public void clickDropDown(String value) throws InterruptedException {
		Select filter = new Select(driver.findElement(By.xpath("(//select[@class='ps-dropdown'])[2]")));
		Thread.sleep(5000);
		filter.selectByValue(value);

		driver.findElement(By.id("CTS_TS_LAND_WRK_SEARCH$span")).click();
		Thread.sleep(5000);

		List<WebElement> list = driver
				.findElements(By.xpath("//div[@class='ps_box-group psc_layout card_group_box  green_color_badge']"));
		int size = list.size();

		for (int i = 0; i < size; i++) {
			String date = driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$" + i)).getText();
			String status = driver.findElement(By.id("CTS_TS_LAND_PER_CTS_TS_STATUS_LAND$" + i)).getText();
			Thread.sleep(500);
			System.out.println(date + "\n" + status);
		}
		Thread.sleep(5000);
	}
 }
import { test, expect } from '@playwright/test';

test('Mock weather API request for Paris correct', async ({ page }) => {
  await page.route('https://api.weather.com/forecast?city=Paris', route => {
    route.fulfill({
      contentType: 'application/json',
      body: JSON.stringify({
        city: 'Paris',
        temperature: '22°C',
        condition: 'Partly cloudy'
      }),
    });
  });

  await page.goto('http://10.43.93.133:5501/mock-apis/weather.html');

  const cityInput = page.getByPlaceholder('Enter city');
  const searchButton = page.getByText('Get Weather'); 
  const resultDiv = page.locator('#result');
  await cityInput.fill('Paris');
  await searchButton.click();

  await expect(resultDiv).toHaveText('Paris: 22°C, Partly cloudy');
});

test('Mock weather API request for Paris 500 error', async ({ page }) => {
  await page.route('/forecast?city=Paris', route => {
    route.fulfill({
      status: 500,
      contentType: 'application/json',
      body: JSON.stringify({ error: 'Internal Server Error' }),
    });
  });

  await page.goto('http://10.43.93.133:5501/mock-apis/weather.html');

  const cityInput = page.getByPlaceholder('Enter city');
  const searchButton = page.getByText('Get Weather');
  await cityInput.fill('Paris');
  await searchButton.click();

  const resultText = await page.locator('#result').innerText();
  expect(resultText === '' || resultText.includes('error') || resultText.includes('undefined: undefined, undefined') || resultText.includes('Error')).toBeTruthy();
});

test('Modifying harry potter API response', async ({ page }) => {
  await page.route(/\/characters(\?.*)?$/, async route => {
    const response = await route.fetch();
    const json = await response.json();
    json.data.push({
      attributes: {
        name: 'Hector Emil Grijalva Sanchez',
        house: 'Ravenclaw',
      }
    });
    json.data = json.data.filter(user => user.attributes.name !== 'Alexander William');
    await route.fulfill({ response, json });
  });

  await page.goto('http://10.43.93.133:5501/mock-apis/harry-potter-list.html');

  const userList = page.locator('#userList');
  await expect(userList).toContainText('Hector Emil Grijalva Sanchez - Ravenclaw');
  await expect(userList).not.toContainText('Alexander William - Gryffindor');
});

test('Records or updates the HAR file', async ({ page }) => {
  await page.routeFromHAR('./hars/harry-potter/harry-potter.har', {
    url: '/characters?page[size]=100&page[number]=2',
    update: true,
  });

  await page.goto('http://10.43.93.133:5501/mock-apis/harry-potter-list.html');

  const userList = page.locator('#userList');
  await expect(userList).toContainText('Albus Percival Wulfric Brian Dumbledore - Gryffindor');
});

test('Gets the json from HAR and checks the new user', async ({ page }) => {
  await page.routeFromHAR('./hars/harry-potter/harry-potter.har', {
    url: '/characters?page[size]=100&page[number]=2',
    update: false,
  });

  await page.goto('http://10.43.93.133:5501/mock-apis/harry-potter-list.html');

  const userList = page.locator('#userList');
  await expect(userList).toContainText('Hector Emil Grijalva Sanchez - Ravenclaw');
});
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        'blue-2': '#86bbd8',   
        'blue': '#003049',  
        'red': '#d62828',
        'orange': '#f77f00',
        'yellow': '#fcbf49',
        'bone': '#eae2b7',
      },
    },    
  },
  plugins: [],
};

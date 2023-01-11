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
        'paleta2-claro': '#fce1d8',
        'paleta2-rojo': '#900d07',
        'paleta2-naranja': '#f48052',
        'paleta2-azulverd': '#2a797b',
        'paleta2-orange-claro': '#f6a787',
        'paleta2-dark': ' #1d5352',
        'paleta2-red-claro':'#b25550'
      },
    },    
  },
  plugins: [],
};

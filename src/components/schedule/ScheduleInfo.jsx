function ScheduleInfo() {
  return (
    <>
      <div className="bg-slate-200 w-full px-5 rounded overflow-visible shadow-lg pb-5">
        <div>
          <div className="flex justify-between pt-3">
            <span className="text-base font-medium ">Progreso</span>
            <span className="text-sm font-medium ">45%</span>
          </div>
          <div className="w-full bg-gray-200 rounded-full h-2.5 dark:bg-gray-700">
            <div className="bg-amber-300 h-2.5 rounded-full w-1/2"></div>
          </div>
        </div>
      </div>
    </>
  );
}

export default ScheduleInfo;

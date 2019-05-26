export class Activity {
  private title: string;
  private id: number;
  private startDate: string;
  private endDate: string;
  private progress: number;
  private next: Array<number>;
  private duration: number;

  constructor(title: string, id: number, startDate: string, endDate: string, progress: number, next: Array<number>, duration: number) {
    this.title = title;
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.progress = progress;
    this.next = next;
    this.duration = duration;
  }

  public Title() {
    return this.title;
  }

  public Id() {
    return this.id;
  }

  public StartDate() {
    return this.startDate;
  }

  public EndDate() {
    return this.endDate;
  }

  public Progress() {
    return this.progress;
  }

  public Duration() {
    return this.duration;
  }
}

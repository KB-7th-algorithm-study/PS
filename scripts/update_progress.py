"""problems.yml과 각자 폴더를 비교해서 README.md의 진행률 표를 갱신한다.

각자 폴더 안의 하위 구조는 자유. 폴더 트리 전체를 재귀적으로 뒤져서
파일명이 "[플랫폼] 번호.확장자" 형식이면 problems.yml에 등록된 문제인지만 확인한다.
(이름/주차는 파일 경로에 적을 필요 없음 — platform+번호만으로 매칭)
"""
import re
from pathlib import Path

import yaml

ROOT = Path(__file__).resolve().parents[1]
PROBLEMS_FILE = ROOT / "problems.yml"
README_FILE = ROOT / "README.md"

START_MARKER = "<!-- PROGRESS:START -->"
END_MARKER = "<!-- PROGRESS:END -->"

FILENAME_RE = re.compile(r"^\[(?P<platform>[A-Za-z]+)\]\s+(?P<number>[A-Za-z0-9]+)\.\w+$")

EXCLUDE_DIRS = {".git", ".github", "scripts"}
BAR_WIDTH = 20
CODE_EXTENSIONS = {"java", "py", "cpp", "c", "js", "kt"}


def load_problems_yml():
    return yaml.safe_load(PROBLEMS_FILE.read_text(encoding="utf-8")) or {}


def load_valid_keys(data):
    """problems.yml의 모든 주차를 (platform, number) 집합으로 평탄화한다."""
    keys = set()
    for week_block in data.get("weeks", []):
        for p in week_block["problems"]:
            keys.add((p["platform"].upper(), str(p["number"])))
    return keys


def load_inactive_members(data):
    return set(data.get("inactiveMembers", []))


def find_member_dirs():
    return sorted(
        d for d in ROOT.iterdir()
        if d.is_dir() and not d.name.startswith(".") and d.name not in EXCLUDE_DIRS
    )


def scan_member(member_dir, valid_keys):
    solved = set()
    for f in member_dir.rglob("*"):
        if not f.is_file():
            continue
        m = FILENAME_RE.match(f.name)
        if not m:
            continue
        key = (m.group("platform").upper(), m.group("number"))
        if key in valid_keys:
            solved.add(key)
    return solved


def render_bar(pct):
    filled = round(BAR_WIDTH * pct / 100)
    return "█" * filled + "░" * (BAR_WIDTH - filled)


def count_total_files(member_dir):
    """이름 규칙과 무관하게, 지금까지 올린 코드 파일 총 개수를 센다."""
    count = 0
    for f in member_dir.rglob("*"):
        if f.is_file() and f.suffix.lstrip(".").lower() in CODE_EXTENSIONS:
            count += 1
    return count


def build_section(valid_keys, inactive_members):
    total = len(valid_keys)
    active_rows = []
    inactive_rows = []
    for member_dir in find_member_dirs():
        name = member_dir.name
        total_count = count_total_files(member_dir)
        if name in inactive_members:
            inactive_rows.append((name, total_count))
            continue
        solved = scan_member(member_dir, valid_keys)
        pct = round(len(solved) / total * 100) if total else 0
        active_rows.append((name, len(solved), pct, total_count))

    active_rows.sort(key=lambda r: -r[2])

    lines = [START_MARKER, ""]
    lines.append("| 이름 | 진행률 | 완료 | 누적 풀이 |")
    lines.append("|:--|:--|:--:|:--:|")
    for name, solved, pct, total_count in active_rows:
        lines.append(f"| {name} | `{render_bar(pct)}` {pct}% | {solved}/{total} | {total_count}개 |")
    for name, total_count in inactive_rows:
        lines.append(f"| {name} | 🛌 참여 안 함 | - | {total_count}개 |")
    lines.append("")
    lines.append(END_MARKER)
    return "\n".join(lines)


def update_readme(section):
    readme = README_FILE.read_text(encoding="utf-8")
    pattern = re.compile(re.escape(START_MARKER) + r".*?" + re.escape(END_MARKER), re.DOTALL)
    if pattern.search(readme):
        readme = pattern.sub(section, readme)
    else:
        readme = readme.rstrip("\n") + "\n\n" + section + "\n"
    README_FILE.write_text(readme, encoding="utf-8")


def main():
    data = load_problems_yml()
    valid_keys = load_valid_keys(data)
    inactive_members = load_inactive_members(data)
    section = build_section(valid_keys, inactive_members)
    update_readme(section)


if __name__ == "__main__":
    main()
